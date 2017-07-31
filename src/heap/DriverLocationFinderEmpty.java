package heap;

/**
 * Date 30/06/17
 *
 * @author Ankit Jain
 * <p>
 * STDIN
 * <p>
 * 6
 * Driver1
 * 30
 * location1
 * Driver1
 * 34
 * location6
 * Driver2
 * 20
 * location2
 * Driver2
 * 10
 * location3
 * Driver1
 * 10
 * location4
 * Driver1
 * 40
 * location5
 * 2
 * Driver1
 * 32
 * Driver3
 * 5
 */
// Driver1, Driver2, DriverN

// Driver1 - 3:30 PM - Koramanagala, 5 PM - Indiranagar
// Driver2 - 6 PM - Indiranagar

// 2 API - the 1st api adds new driver with their timestamp
// 2nd API - driverid - time Driver1 4PM - the last know location
// m drivers, n locations for each driver, q - query q >> m x n
// For 1 driver -> addDriverLocationEntry => logn,
//    m divers -> mlogn
// n loctions -> mnlogn

//1 query is getLastKnownLocation -> logn
//q queries = q*logn

//Overall -> mnlogn + q*logn = qlogn

import java.util.*;

public class DriverLocationFinderEmpty {

    HashMap<String, TreeMap<Long, String>> map;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        DriverLocationFinderEmpty solution = new DriverLocationFinderEmpty();
        solution.map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String name = scanner.next();
            long time = scanner.nextLong();
            String location = scanner.next();
            solution.addDriverLocationEntry(new DriverDetails(name, time, location));
        }

        count = scanner.nextInt();
        for (int j = 0; j < count; j++) {
            System.out.println(solution.getLastKnownLocation(scanner.next(), scanner.nextLong()));
        }

    }

    static class DriverDetails {
        private final String name;
        private final long time;
        private final String location;

        DriverDetails(String name, long time, String location) {
            this.name = name;
            this.time = time;
            this.location = location;
        }

        String getName() {
            return name;
        }

        long getTime() {
            return time;
        }

        String getLocation() {
            return location;
        }

    }

    private void addDriverLocationEntry(DriverDetails driverDetails) {
        if (!map.containsKey(driverDetails.name))
            map.put(driverDetails.name, new TreeMap<>());
        map.get(driverDetails.name).put(driverDetails.time, driverDetails.location);
    }

    private String getLastKnownLocation(String name, long time) {
        TreeMap<Long, String> tMap = map.get(name);
        if (tMap == null) return null;
        Map.Entry<Long, String> me = tMap.floorEntry(time);
        return me != null ? me.getValue() : null;
    }

}

