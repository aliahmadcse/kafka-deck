-- keyspace is like a database in cassandra

CREATE KEYSPACE IF NOT EXISTS easy_taxi
      WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE easy_taxi;


-- Partition Key:
--    user_id: Ensures rides for a specific user are stored together.
-- Clustering Columns:
--    start_time: Helps order the rides by their start time for a user.
--    ride_id: Maintains uniqueness in case two rides have the same start time.
-- Query Optimization:
--    This structure allows efficient querying for all rides by a specific user, sorted by time.
CREATE TABLE ride_details
(
  ride_id        UUID,      -- Unique identifier for each ride
  user_id        UUID,      -- ID of the user who booked the ride
  driver_id      UUID,      -- ID of the driver assigned to the ride
  start_location TEXT,      -- Starting point of the ride
  end_location   TEXT,      -- Ending point of the ride
  ride_status    TEXT,      -- Status: e.g., 'Completed', 'Ongoing', 'Cancelled'
  start_time     TIMESTAMP, -- Ride start time
  end_time       TIMESTAMP, -- Ride end time
  fare           DECIMAL,   -- Total fare for the ride
  PRIMARY KEY ((user_id), start_time, ride_id)
);


INSERT INTO ride_details (ride_id, user_id, driver_id, start_location, end_location, ride_status, start_time, end_time,
                          fare)
VALUES (uuid(), uuid(), uuid(), 'Downtown', 'Airport', 'Completed', '2025-01-18 10:00:00', '2025-01-18 10:30:00',
        25.50);


// strange, it does not take user_id in quotes 🙄
SELECT *
FROM ride_details
WHERE user_id = c1d33c84-3335-4350-941a-372cc1700f5d
ORDER BY start_time DESC;
