package com.example.blabladem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Basic
    @Column(name = "file", nullable = false)
    private byte[] file;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id")
    private Task task;
}
