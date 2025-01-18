package codes.aliahmad.demo.entity;


import codes.aliahmad.demo.entity.key.RideDetailKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table("ride_details")
@Getter
@Setter
@ToString
public class RideDetail
{
  @PrimaryKey
  private RideDetailKey key;

  @Column("start_location")
  private String startLocation;
  @Column("end_location")
  private String endLocation;
  @Column("ride_status")
  private String rideStatus;
  @Column("start_time")
  private Instant startTime;
  @Column("driver_id")
  private UUID driverId;
  @Column("end_time")
  private Instant endTime;
  private BigDecimal fare;
}
