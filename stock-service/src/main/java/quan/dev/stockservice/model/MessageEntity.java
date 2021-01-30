package quan.dev.stockservice.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Table(name = "stock_msg")
@Entity
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "msg_content")
    public String msgContent;

    @CreationTimestamp
    @Column(name = "created_time")
    public Timestamp createdTime;

    @CreationTimestamp
    @Column(name = "created_datetime")
    public LocalDateTime createdDatetime;

}
