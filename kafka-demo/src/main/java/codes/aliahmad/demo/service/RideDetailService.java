package codes.aliahmad.demo.service;

import codes.aliahmad.demo.entity.RideDetail;

import java.util.List;
import java.util.UUID;

/**
 * Description - This interface provides the methods to interact with the RideDetails entity.
 *
 * @author Ali Ahmad
 * @since 1.0
 */
public interface RideDetailService
{
  List<RideDetail> getRidesByUserId(UUID userId);

  RideDetail saveRide(RideDetail rideDetails);
}
