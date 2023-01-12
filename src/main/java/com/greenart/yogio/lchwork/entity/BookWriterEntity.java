package com.greenart.yogio.lchwork.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_writer_book")
@Builder
public class BookWriterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wb_seq") private Long wbSeq;
    @Column(name = "wb_book_seq") private Long wbBookSeq;
    @Column(name = "wb_writer_seq") private Long wbWriterSeq;
}
