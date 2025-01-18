package codes.aliahmad.demo.service.impl;

import codes.aliahmad.demo.entity.RideDetail;
import codes.aliahmad.demo.repository.RideDetailsRepository;
import codes.aliahmad.demo.service.RideDetailService;
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
public class RideDetailServiceImpl implements RideDetailService
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
