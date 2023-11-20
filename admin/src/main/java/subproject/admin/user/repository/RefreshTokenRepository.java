package subproject.admin.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import subproject.admin.user.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByKey(String key);
}
