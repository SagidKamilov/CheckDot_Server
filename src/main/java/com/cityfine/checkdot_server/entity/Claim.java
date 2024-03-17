package com.cityfine.checkdot_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "claims")
public class Claim {
    @Id
    private @GeneratedValue() Long claimId;

    @Column
    private String heading;

    @Column
    private String description;

    @Column
    private String path_image;

    @Column
    private String address;

    @Column
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
}
