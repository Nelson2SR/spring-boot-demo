package ZonedDateTimeUtilTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * @author rong.su 22/9/22
 */

public class ZonedDateTimeUtilTest {

    @Test
    void testZonedDateTime() {
        // Last time transition date Sun, 25 Mar 2018 2:00
        LocalDateTime localDateTimeBeforeDST = LocalDateTime.of(2018, 3, 25, 0, 0);

        ZoneId italianZoneId = ZoneId.of("Europe/Rome");
        ZonedDateTime zonedDateTimeBeforeDST = localDateTimeBeforeDST.atZone(italianZoneId);

        ArrayList<ZonedDateTime> arrTZ = new ArrayList<ZonedDateTime>();

        for (int i = 0; i < 92; i++){
            ZonedDateTime zonedDateTimeAfterDST = zonedDateTimeBeforeDST.plus(15*(i+1), ChronoUnit.MINUTES);
            arrTZ.add(zonedDateTimeAfterDST);
        }

        for (int i = 0; i < arrTZ.size(); i++){
            ZonedDateTime zdt1 = arrTZ.get(i).withZoneSameInstant(ZoneId.of("UTC+1"));
            System.out.println("[UTC+01] " + zdt1 + " <--> [DST]: ," + arrTZ.get(i) + "<-->" + (i+1));
        }

        Long deltaBetweenDatesInMinutes = ChronoUnit.MINUTES.between(
            arrTZ.get(0).withZoneSameInstant(ZoneId.of("UTC")),
            arrTZ.get(arrTZ.size() - 1).withZoneSameInstant(ZoneId.of("UTC")));

        Assertions.assertEquals(deltaBetweenDatesInMinutes.longValue(), 1365L);
    }

    @Test
    void testDstToUTC() {
        ZoneId romeZoneId = ZoneId.of("Europe/Rome");
        ZonedDateTime romeNow = ZonedDateTime.now(romeZoneId).truncatedTo(ChronoUnit.SECONDS);
        System.out.println("Rome offset is: " + romeNow.getOffset());

        ZonedDateTime romeUtc = romeNow.withZoneSameInstant(ZoneId.of("UTC+1"));
        System.out.println("[UTC+1]:" + romeUtc.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) + " <---> [DST]: " + romeNow);

        System.out.println("ISO_ZONE_DATE_TIME Format: " + romeUtc.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println("ISO_OFFSET_DATE_TIME: " + romeUtc.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        Assertions.assertEquals(romeUtc.getZone(), ZoneId.of("UTC+1"));
    }

    static String getTzOffsetString(ZoneId zoneId, Instant instant) {
        return ZonedDateTime.ofInstant(instant, zoneId).getOffset().toString();
    }

    @Test
    void testWareDst_getOffset(){
//        Instant instant = Instant.now();
        Instant instant1 = Instant.parse("2018-03-25T00:00:00Z");
        Instant instant2 = Instant.parse("2018-03-25T03:00:00Z");

        //ZoneId italianZoneId = ZoneId.of("Europe/Rome");
        ZoneId italianZoneId = ZoneId.of("Asia/Singapore");

        System.out.printf("%-35s: %-6s%n",
            "Europe/Rome", getTzOffsetString(italianZoneId, instant1));
        System.out.printf("%-35s: %-6s%n",
            "Europe/Rome", getTzOffsetString(italianZoneId, instant2));
    }

}
