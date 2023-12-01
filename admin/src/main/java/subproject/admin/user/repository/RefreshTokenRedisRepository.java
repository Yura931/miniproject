package subproject.admin.user.repository;

import org.springframework.data.repository.CrudRepository;
import subproject.admin.user.entity.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {

}
