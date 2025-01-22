package codes.aliahmad.demo;

import codes.aliahmad.demo.entity.RideDetail;
import codes.aliahmad.demo.entity.key.RideDetailKey;
import codes.aliahmad.demo.port.RideDetailPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class KafkaDemoApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(KafkaDemoApplication.class, args);
  }


  @Component
  @Slf4j
  public static class RideDetailsCommandLineRunner implements CommandLineRunner
  {

    private final RideDetailPort rideDetailsService;

    public RideDetailsCommandLineRunner(RideDetailPort rideDetailsService)
    {
      this.rideDetailsService = rideDetailsService;
    }

    @Override
    public void run(String... args) throws Exception
    {
      // Add a ride
      UUID userId = UUID.randomUUID();
      UUID rideId = UUID.randomUUID();
      Instant startTime = Instant.now();

      RideDetailKey key = new RideDetailKey(userId, startTime, rideId);

      RideDetail newRide = new RideDetail();
      newRide.setKey(key);
      newRide.setDriverId(UUID.randomUUID());
      newRide.setStartLocation("Downtown");
      newRide.setEndLocation("Airport");
      newRide.setRideStatus("Completed");
      newRide.setEndTime(startTime.plusSeconds(1800)); // 30 minutes later
      newRide.setFare(BigDecimal.valueOf(25.50));

      rideDetailsService.saveRide(newRide);
      log.info("Ride added: {}", newRide);

      // Fetch rides for the same user
      log.info("Fetching rides for user: {}", userId);
      List<RideDetail> rides = rideDetailsService.getRidesByUserId(userId);
      rides.forEach(ride -> log.info("Ride: {}", ride));

      // Add another ride for the same user to test ordering
      UUID secondRideId = UUID.randomUUID();
      Instant secondStartTime = startTime.minusSeconds(3600); // 1 hour earlier

      RideDetailKey secondKey = new RideDetailKey(userId, secondStartTime, secondRideId);

      RideDetail secondRide = new RideDetail();
      secondRide.setKey(secondKey);
      secondRide.setDriverId(UUID.randomUUID());
      secondRide.setStartLocation("Mall");
      secondRide.setEndLocation("Stadium");
      secondRide.setRideStatus("Cancelled");
      secondRide.setEndTime(secondStartTime.plusSeconds(1200)); // 20 minutes later
      secondRide.setFare(BigDecimal.valueOf(15.00));

      rideDetailsService.saveRide(secondRide);
      log.info("Second ride added: {}", secondRide);

      // Fetch rides again to verify ordering
      log.info("Updated list of rides for user: {}", userId);
      rides = rideDetailsService.getRidesByUserId(userId);
      rides.forEach(ride -> log.info("Ride: {}", ride));
    }
  }
}
