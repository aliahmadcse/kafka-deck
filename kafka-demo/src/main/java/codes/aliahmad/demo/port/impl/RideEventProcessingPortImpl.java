package codes.aliahmad.demo.port.impl;

import codes.aliahmad.demo.dto.RideEvent;
import codes.aliahmad.demo.entity.RideDetail;
import codes.aliahmad.demo.mapper.RideEventMapper;
import codes.aliahmad.demo.port.RideDetailPort;
import codes.aliahmad.demo.port.RideEventProcessingPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RideEventProcessingPortImpl implements RideEventProcessingPort
{
  private final RideDetailPort rideDetailPort;
  private final ObjectMapper objectMapper;

  @Override
  public void processRideEvent(String rideEvent)
  {
    try
    {
      log.info("Consuming ride event: {}", rideEvent);

      RideEvent rideEventObj = objectMapper.readValue(rideEvent, RideEvent.class);
      RideDetail rideDetail = RideEventMapper.toRideDetail(rideEventObj);
      rideDetailPort.saveRide(rideDetail);
    }
    catch (Exception e)
    {
      log.error("Error processing ride event: {}", rideEvent, e);
    }
  }
}
