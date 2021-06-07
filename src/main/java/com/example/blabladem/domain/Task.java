package com.example.blabladem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant creationDate;

    private String topic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskAttachment> attachments = new ArrayList<>();
}