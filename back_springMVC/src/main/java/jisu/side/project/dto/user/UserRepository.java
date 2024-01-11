package jisu.side.project.dto.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, String> {
    Member findOneById(String id);
}
