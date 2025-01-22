package codes.aliahmad.demo.mapper;


import codes.aliahmad.demo.dto.RideEvent;
import codes.aliahmad.demo.entity.RideDetail;
import codes.aliahmad.demo.entity.key.RideDetailKey;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RideEventMapper
{
  public static RideDetail toRideDetail(RideEvent rideEvent)
  {
    RideDetailKey rideDetailKey = new RideDetailKey(rideEvent.getUserId(), rideEvent.getStartTime(), rideEvent.getRideId());

    RideDetail rideDetail = new RideDetail();
    rideDetail.setKey(rideDetailKey);
    rideDetail.setStartLocation(rideEvent.getStartLocation());
    rideDetail.setEndLocation(rideEvent.getEndLocation());
    rideDetail.setRideStatus(rideEvent.getRideStatus());
    rideDetail.setDriverId(rideEvent.getDriverId());
    rideDetail.setEndTime(rideEvent.getEndTime());
    rideDetail.setFare(rideEvent.getFare());

    return rideDetail;
  }
}
