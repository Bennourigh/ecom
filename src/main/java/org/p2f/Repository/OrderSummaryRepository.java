package org.p2f.Repository;

import org.p2f.ReadSide.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface OrderSummaryRepository extends JpaRepository<OrderSummary, String> {
}
