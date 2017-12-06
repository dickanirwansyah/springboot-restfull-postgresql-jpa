package dicka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class ConfigSecure extends WebSecurityConfigurerAdapter{

    private static final String SQL_PENGGUNA = "select username, password, " +
            "actives as enabled from pengguna where username=?";

    private static final String SQL_INNER_ROLE
            = "select p.username, r.nama as authority " +
            "from pengguna p join pengguna_role pr on p.idpengguna=pr.idpengguna " +
            "join role r on pr.idrole=r.idrole " +
            "where p.username = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_PENGGUNA)
                .authoritiesByUsernameQuery(SQL_INNER_ROLE);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.authorizeRequests()
                .antMatchers("/api").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/pengguna/").hasAnyRole("ADMIN")
                .antMatchers("/api/products").hasAnyRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/api/pengguna/");
    }

}
