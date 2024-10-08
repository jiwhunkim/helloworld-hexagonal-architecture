package com.helloworld.common.adapter.outside.persistence.mysql.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity {
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "DATETIME(6) DEFAULT now(6) not null")
    var createdAt: ZonedDateTime = ZonedDateTime.now()
        protected set

    @UpdateTimestamp
    @Column(
        nullable = false,
        columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) not null",
    )
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
        protected set

    @CreatedBy
    @Column(name = "created_by", nullable = false, columnDefinition = "VARCHAR(64) NOT NULL DEFAULT ''")
    var createdBy: String = ""
        protected set

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false, columnDefinition = "VARCHAR(64) NOT NULL DEFAULT ''")
    var updatedBy: String = ""
        protected set
}
