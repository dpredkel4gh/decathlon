package io.farel.decathlon.util;

import io.farel.decathlon.model.DisciplineUnit;
import io.farel.decathlon.model.Pair;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static io.farel.decathlon.model.DisciplineUnit.*;

@RequiredArgsConstructor
public class UnitConverter {
    private static final BigDecimal SECONDS_IN_MINUTES = BigDecimal.valueOf(60);
    private static final BigDecimal MILLISECONDS_IN_SECONDS = BigDecimal.valueOf(1000);
    private static final BigDecimal CENTIMETRES_IN_METRES = BigDecimal.valueOf(100);
    private static final int SCALE = 2;
    private static final RoundingMode roundingMode = RoundingMode.UP;

    // date time formatter should be passed here from upstream converters
    // as we may have different formats across different source types
    private static final char TIME_DELIMITER = '.';
    private final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.MINUTE_OF_HOUR, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendLiteral(TIME_DELIMITER).appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .appendLiteral(TIME_DELIMITER).appendValue(ChronoField.NANO_OF_SECOND, 2)
            .toFormatter(Locale.ENGLISH);

    private final Map<Pair<DisciplineUnit, DisciplineUnit>, Function<String, BigDecimal>> unitConversionMapping = new HashMap<>() {{
        put(Pair.of(SECONDS, MINUTES), str -> new BigDecimal(str).divide(SECONDS_IN_MINUTES, SCALE, roundingMode));
        put(Pair.of(MINUTES, SECONDS), str -> convertMinutesToSeconds(str));
        put(Pair.of(CENTIMETRES, METRES), str -> new BigDecimal(str).divide(CENTIMETRES_IN_METRES, SCALE, roundingMode));
        put(Pair.of(METRES, CENTIMETRES), str -> new BigDecimal(str).multiply(CENTIMETRES_IN_METRES));
    }};

    public float convert(String value, DisciplineUnit from, DisciplineUnit to) {
        if (from.equals(to)) {
            return Float.parseFloat(value);
        }
        return getMapping(from, to).apply(value).floatValue();
    }

    private Function<String, BigDecimal> getMapping(DisciplineUnit from, DisciplineUnit to) {
        return Optional.ofNullable(unitConversionMapping.get(Pair.of(from, to)))
                .orElseThrow(() -> new IllegalArgumentException("incorrect unit conversion requested"));
    }

    private BigDecimal convertMinutesToSeconds(String value) {
        try {
            TemporalAccessor parsed = dateTimeFormatter.parse(value);

            return new BigDecimal(parsed.get(ChronoField.MINUTE_OF_HOUR)).multiply(SECONDS_IN_MINUTES)
                    .add(new BigDecimal(parsed.get(ChronoField.SECOND_OF_MINUTE)))
                    .add(new BigDecimal(parsed.get(ChronoField.NANO_OF_SECOND))
                            .divide(MILLISECONDS_IN_SECONDS, SCALE, roundingMode));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("invalid date format");
        }
    }
}
