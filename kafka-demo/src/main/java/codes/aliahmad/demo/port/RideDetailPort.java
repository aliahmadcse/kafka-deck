package codes.aliahmad.demo.port;

import codes.aliahmad.demo.entity.RideDetail;

import java.util.List;
import java.util.UUID;

/**
 * Description - This interface provides the methods to interact with the RideDetails entity.
 *
 * @author Ali Ahmad
 * @since 1.0
 */
public interface RideDetailPort
{
  List<RideDetail> getRidesByUserId(UUID userId);

  RideDetail saveRide(RideDetail rideDetails);
}
