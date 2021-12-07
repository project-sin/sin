package sin.sin.domain.orders;

import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select distinct orders from Orders orders "
        + "join fetch orders.orderProductLists "
        + "where orders.member.id = :memberId")
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    List<Orders> findAllByMemberId(@Param("memberId") Long memberId);
}
