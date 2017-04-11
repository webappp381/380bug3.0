package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Ticket;
import edu.ouhk.comps380f.model.TicketUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private DataSource dataSource;
    private JdbcOperations jdbcOp;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    private static final class TicketRowMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int i) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setCustomerName(rs.getString("customername"));
            ticket.setSubject(rs.getString("subject"));
            ticket.setBody(rs.getString("body"));
            ticket.setCategories(rs.getString("categories"));
            return ticket;
        }
    }

//    private static final String SQL_INSERT_TICKET
//            = "insert into ticket (id,customername,subject,body,categories) values (1,'p','f','d','lab')";
        private static final String SQL_INSERT_TICKET
            = "insert into ticket (customername,subject,body,categories) values (?,?,?,?)";
   // private static final String SQL_INSERT_ROLE
     //       = "insert into user_roles (username, role) values (?, ?)";

    public void create(Ticket ticket) {
        jdbcOp.update(SQL_INSERT_TICKET,
                ticket.getCustomerName(),
                ticket.getSubject(),               
                ticket.getBody(),
                ticket.getCategories());
//        for (String role : user.getRoles()) {
//            jdbcOp.update(SQL_INSERT_ROLE,
//                    user.getUsername(),
//                    role);             
    }


    private static final String SQL_SELECT_ALL_TICKET
            = "select id, customername, subject, body, categories from ticket";
  //  private static final String SQL_SELECT_ROLES
    //        = "select username, role from user_roles where username = ?";

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList(SQL_SELECT_ALL_TICKET);

        for (Map<String, Object> row : rows) {
            Ticket ticket = new Ticket();
            
            int id = (int)row.get("id");
            ticket.setId(id);       
            
            String customername = (String)row.get("customername");
            ticket.setCustomerName(customername);  
            
            String subject = (String)row.get("subject");
            ticket.setSubject(subject);
            
            String body = (String)row.get("body");
            ticket.setBody(body);
            
            String categories = (String)row.get("categories");
            ticket.setCategories(categories);
            
            tickets.add(ticket);
        }
        return tickets;
    }
    private static final String SQL_SELECT_TICKET
            = "select * from ticket where id = ?";

    @Override
    public Ticket findById(int id) {
        Ticket ticket = jdbcOp.queryForObject(SQL_SELECT_TICKET, new TicketRowMapper(), id);
        return ticket;
    }
    
    private static final String SQL_SELECT_MAX
            = "select * from ticket where id = (select MAX(id) from ticket)";
    @Override
    public int maxId() {
        Ticket idobject = jdbcOp.queryForObject(SQL_SELECT_MAX, new TicketRowMapper());
        int id=(int)idobject.getId();
        return id;
    }

//    private static final String SQL_DELETE_USER
//            = "delete from users where username = ?";
//    private static final String SQL_DELETE_ROLES
//            = "delete from user_roles where username = ?";
//
//    @Override
//    public void deleteByUsername(String username) {
//        jdbcOp.update(SQL_DELETE_ROLES, username);
//        jdbcOp.update(SQL_DELETE_USER, username);
//    }

}
