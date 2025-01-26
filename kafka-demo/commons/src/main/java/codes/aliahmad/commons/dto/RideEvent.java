package codes.aliahmad.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideEvent
{
  private UUID userId;
  private Instant startTime;
  private UUID rideId;
  private String startLocation;
  private String endLocation;
  private String rideStatus;
  private UUID driverId;
  private Instant endTime;
  private BigDecimal fare;
}
