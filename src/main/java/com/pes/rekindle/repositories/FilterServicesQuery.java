
package com.pes.rekindle.repositories;

public class FilterServicesQuery {

    public String getGeolocationQuery() {
        return "SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng, distance\r\n"
                +
                "FROM (\r\n" +
                "   SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n"
                +
                "       p.radius,\r\n" +
                "       p.distance_unit\r\n" +
                "                * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" +
                "                * COS(RADIANS(l.positionLat))\r\n" +
                "                * COS(RADIANS(p.longpoint - l.positionLng))\r\n" +
                "                + SIN(RADIANS(p.latpoint))\r\n" +
                "                * SIN(RADIANS(l.positionLat)))) AS distance\r\n" +
                "   FROM Lodge AS l\r\n" +
                "   JOIN (   /* these are the query parameters */\r\n" +
                "       SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" +
                "               ?3 AS radius,      111.045 AS distance_unit\r\n" +
                "   ) AS p ON 1=1\r\n" +
                "   WHERE not l.ended and\r\n" +
                "       l.positionLat\r\n" +
                "    BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" +
                "        AND p.latpoint  + (p.radius / p.distance_unit)\r\n" +
                "   AND l.positionLng\r\n" +
                "    BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +
                "        AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +

                "UNION" +

                "   SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n"
                +
                "       p.radius,\r\n" +
                "       p.distance_unit\r\n" +
                "                * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" +
                "                * COS(RADIANS(l.positionLat))\r\n" +
                "                * COS(RADIANS(p.longpoint - l.positionLng))\r\n" +
                "                + SIN(RADIANS(p.latpoint))\r\n" +
                "                * SIN(RADIANS(l.positionLat)))) AS distance\r\n" +
                "   FROM Donation AS l\r\n" +
                "   JOIN (   /* these are the query parameters */\r\n" +
                "       SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" +
                "               ?3 AS radius,      111.045 AS distance_unit\r\n" +
                "   ) AS p ON 1=1\r\n" +
                "   WHERE not l.ended and\r\n" +
                "       l.positionLat\r\n" +
                "    BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" +
                "        AND p.latpoint  + (p.radius / p.distance_unit)\r\n" +
                "   AND l.positionLng\r\n" +
                "    BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +
                "        AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +

                "UNION" +

                "   SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n"
                +
                "       p.radius,\r\n" +
                "       p.distance_unit\r\n" +
                "                * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" +
                "                * COS(RADIANS(l.positionLat))\r\n" +
                "                * COS(RADIANS(p.longpoint - l.positionLng))\r\n" +
                "                + SIN(RADIANS(p.latpoint))\r\n" +
                "                * SIN(RADIANS(l.positionLat)))) AS distance\r\n" +
                "   FROM Job AS l\r\n" +
                "   JOIN (   /* these are the query parameters */\r\n" +
                "       SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" +
                "               ?3 AS radius,      111.045 AS distance_unit\r\n" +
                "   ) AS p ON 1=1\r\n" +
                "   WHERE not l.ended and\r\n" +
                "       l.positionLat\r\n" +
                "    BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" +
                "        AND p.latpoint  + (p.radius / p.distance_unit)\r\n" +
                "   AND l.positionLng\r\n" +
                "    BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +
                "        AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +

                "UNION" +

                "   SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n"
                +
                "       p.radius,\r\n" +
                "       p.distance_unit\r\n" +
                "                * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" +
                "                * COS(RADIANS(l.positionLat))\r\n" +
                "                * COS(RADIANS(p.longpoint - l.positionLng))\r\n" +
                "                + SIN(RADIANS(p.latpoint))\r\n" +
                "                * SIN(RADIANS(l.positionLat)))) AS distance\r\n" +
                "   FROM Education AS l\r\n" +
                "   JOIN (   /* these are the query parameters */\r\n" +
                "       SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" +
                "               ?3 AS radius,      111.045 AS distance_unit\r\n" +
                "   ) AS p ON 1=1\r\n" +
                "   WHERE not l.ended and\r\n" +
                "       l.positionLat\r\n" +
                "    BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" +
                "        AND p.latpoint  + (p.radius / p.distance_unit)\r\n" +
                "   AND l.positionLng\r\n" +
                "    BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +
                "        AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n"
                +
                ") AS d\r\n" +
                "WHERE distance <= radius\r\n" +
                "ORDER BY distance;";
    }

}
