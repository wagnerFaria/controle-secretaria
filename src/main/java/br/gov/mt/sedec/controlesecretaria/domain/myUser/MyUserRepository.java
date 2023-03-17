package br.gov.mt.sedec.controlesecretaria.domain.myUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    UserDetails findByUsername(String username);

    @Query("""
                select u from MyUser u
                where u.username = :username
            """)
    Optional<MyUser> findMyUserByUsername(String username);
}
