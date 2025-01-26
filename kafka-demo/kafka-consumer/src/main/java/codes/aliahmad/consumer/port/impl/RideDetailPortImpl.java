package codes.aliahmad.consumer.port.impl;

import codes.aliahmad.consumer.entity.RideDetail;
import codes.aliahmad.consumer.repository.RideDetailsRepository;
import codes.aliahmad.consumer.port.RideDetailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Description - This class provides the implementation of the RideDetailService interface.
 *
 * @author Ali Ahmad
 * @since 1.0
 */

@Service
@RequiredArgsConstructor
public class RideDetailPortImpl implements RideDetailPort
{
  private final RideDetailsRepository rideDetailsRepository;

  @Override
  public List<RideDetail> getRidesByUserId(UUID userId)
  {
    return rideDetailsRepository.findByKeyUserIdOrderByKeyStartTimeDesc(userId);
  }

  @Override
  public RideDetail saveRide(RideDetail rideDetails)
  {
    return rideDetailsRepository.save(rideDetails);
  }
}
