SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng, distance
FROM (
	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Lodge AS l
	JOIN (   /* these are the query parameters */
		SELECT  :positionLat  AS latpoint,  :positionLng AS longpoint,
				:distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
    JOIN Volunteer v ON l.volunteer = v.mail
	WHERE not l.ended and
		l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
	AND l.expiresOn between :fromDate and :toDate
		and ( (v.averageValoration/v.numberOfValorations) >= :minimumRating
			or :minimumRating is null)
            
	UNION
    
	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Job AS l
	JOIN (   /* these are the query parameters */
		SELECT  :positionLat  AS latpoint,  :positionLng AS longpoint,
				:distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
    JOIN Volunteer v ON l.volunteer = v.mail
	WHERE not l.ended and
		l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
	AND l.expiresOn between :fromDate and :toDate
		and ( (v.averageValoration/v.numberOfValorations) >= :minimumRating
			or :minimumRating is null)
            
	UNION
    
		SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Education AS l
	JOIN (   /* these are the query parameters */
		SELECT  :positionLat  AS latpoint,  :positionLng AS longpoint,
				:distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
    JOIN Volunteer v ON l.volunteer = v.mail
	WHERE not l.ended and
		l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
	AND l.expiresOn between :fromDate and :toDate
		and ( (v.averageValoration/v.numberOfValorations) >= :minimumRating
			or :minimumRating is null)
            
	UNION
    
    
	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Donation AS l
	JOIN (   /* these are the query parameters */
		SELECT  :positionLat  AS latpoint,  :positionLng AS longpoint,
				:distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
    JOIN Volunteer v ON l.volunteer = v.mail
	WHERE not l.ended and
		l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
	AND l.expiresOn between :fromDate and :toDate
		and ( (v.averageValoration/v.numberOfValorations) >= :minimumRating
			or :minimumRating is null)
) AS d
WHERE distance <= radius
ORDER BY distance;

































"SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng, distance\r\n" + 
				"FROM (\r\n" + 
				"	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n" + 
				"		p.radius,\r\n" + 
				"		p.distance_unit\r\n" + 
				"				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" + 
				"				 * COS(RADIANS(l.positionLat))\r\n" + 
				"				 * COS(RADIANS(p.longpoint - l.positionLng))\r\n" + 
				"				 + SIN(RADIANS(p.latpoint))\r\n" + 
				"				 * SIN(RADIANS(l.positionLat)))) AS distance\r\n" + 
				"	FROM Lodge AS l\r\n" + 
				"	JOIN (   /* these are the query parameters */\r\n" + 
				"		SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" + 
				"				?3 AS radius,      111.045 AS distance_unit\r\n" + 
				"	) AS p ON 1=1\r\n" + 
				"	WHERE not l.ended and\r\n" + 
				"		l.positionLat\r\n" + 
				"	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" + 
				"		 AND p.latpoint  + (p.radius / p.distance_unit)\r\n" + 
				"	AND l.positionLng\r\n" + 
				"	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" + 
				"		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" +  
				
				"UNION" +
				
				"	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n" + 
				"		p.radius,\r\n" + 
				"		p.distance_unit\r\n" + 
				"				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" + 
				"				 * COS(RADIANS(l.positionLat))\r\n" + 
				"				 * COS(RADIANS(p.longpoint - l.positionLng))\r\n" + 
				"				 + SIN(RADIANS(p.latpoint))\r\n" + 
				"				 * SIN(RADIANS(l.positionLat)))) AS distance\r\n" + 
				"	FROM Donation AS l\r\n" + 
				"	JOIN (   /* these are the query parameters */\r\n" + 
				"		SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" + 
				"				?3 AS radius,      111.045 AS distance_unit\r\n" + 
				"	) AS p ON 1=1\r\n" + 
				"	WHERE not l.ended and\r\n" + 
				"		l.positionLat\r\n" + 
				"	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" + 
				"		 AND p.latpoint  + (p.radius / p.distance_unit)\r\n" + 
				"	AND l.positionLng\r\n" + 
				"	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" + 
				"		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" +  
				
				"UNION" +
				
				"	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n" + 
				"		p.radius,\r\n" + 
				"		p.distance_unit\r\n" + 
				"				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" + 
				"				 * COS(RADIANS(l.positionLat))\r\n" + 
				"				 * COS(RADIANS(p.longpoint - l.positionLng))\r\n" + 
				"				 + SIN(RADIANS(p.latpoint))\r\n" + 
				"				 * SIN(RADIANS(l.positionLat)))) AS distance\r\n" + 
				"	FROM Job AS l\r\n" + 
				"	JOIN (   /* these are the query parameters */\r\n" + 
				"		SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" + 
				"				?3 AS radius,      111.045 AS distance_unit\r\n" + 
				"	) AS p ON 1=1\r\n" + 
				"	WHERE not l.ended and\r\n" + 
				"		l.positionLat\r\n" + 
				"	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" + 
				"		 AND p.latpoint  + (p.radius / p.distance_unit)\r\n" + 
				"	AND l.positionLng\r\n" + 
				"	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" + 
				"		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" +  
				
				"UNION" +
				
				"	SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, positionLat, positionLng,\r\n" + 
				"		p.radius,\r\n" + 
				"		p.distance_unit\r\n" + 
				"				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" + 
				"				 * COS(RADIANS(l.positionLat))\r\n" + 
				"				 * COS(RADIANS(p.longpoint - l.positionLng))\r\n" + 
				"				 + SIN(RADIANS(p.latpoint))\r\n" + 
				"				 * SIN(RADIANS(l.positionLat)))) AS distance\r\n" + 
				"	FROM Education AS l\r\n" + 
				"	JOIN (   /* these are the query parameters */\r\n" + 
				"		SELECT  ?1  AS latpoint,  ?2 AS longpoint,\r\n" + 
				"				?3 AS radius,      111.045 AS distance_unit\r\n" + 
				"	) AS p ON 1=1\r\n" + 
				"	WHERE not l.ended and\r\n" + 
				"		l.positionLat\r\n" + 
				"	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" + 
				"		 AND p.latpoint  + (p.radius / p.distance_unit)\r\n" + 
				"	AND l.positionLng\r\n" + 
				"	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" + 
				"		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" +  
				") AS d\r\n" + 
				"WHERE distance <= radius\r\n" + 
				"ORDER BY distance;""