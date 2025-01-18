package codes.aliahmad.demo.entity.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.Instant;
import java.util.UUID;

@PrimaryKeyClass
@Getter
@Setter
@AllArgsConstructor
@ToString
public class RideDetailKey
{
  // partition key
  @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
  private UUID userId;

  // ordinal is the order of the clustering column in the primary key
  // clustering column 1
  @PrimaryKeyColumn(name = "start_time", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
  private Instant startTime;

  // clustering column 2
  @PrimaryKeyColumn(name = "ride_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
  private UUID rideId;

}
