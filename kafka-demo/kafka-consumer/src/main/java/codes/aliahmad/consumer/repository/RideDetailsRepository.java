package codes.aliahmad.consumer.repository;


import codes.aliahmad.consumer.entity.RideDetail;
import codes.aliahmad.consumer.entity.key.RideDetailKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RideDetailsRepository extends CassandraRepository<RideDetail, RideDetailKey>
{
  List<RideDetail> findByKeyUserIdOrderByKeyStartTimeDesc(UUID userId);
}
