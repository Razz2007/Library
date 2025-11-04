package com.racinger.librarySystem.Library.Factory;

import com.racinger.librarySystem.Library.DTO.ReservationDto;
import com.racinger.librarySystem.Library.Entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationFactory {

    public ReservationDto createReservationDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());

        if (reservation.getStudent() != null) {
            dto.setStudentId(reservation.getStudent().getId());
            dto.setStudentName(reservation.getStudent().getFirstName() + " " + reservation.getStudent().getLastName());
        }

        if (reservation.getBook() != null) {
            dto.setBookId(reservation.getBook().getId());
            dto.setBookTitle(reservation.getBook().getTitle());
        }

        dto.setReservationDate(reservation.getReservationDate());
        dto.setExpirationDate(reservation.getExpirationDate());
        dto.setStatus(reservation.getStatus().toString());

        return dto;
    }

    public Reservation createReservationEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setExpirationDate(dto.getExpirationDate());

        if (dto.getStatus() != null) {
            reservation.setStatus(Reservation.ReservationStatus.valueOf(dto.getStatus()));
        }

        return reservation;
    }
}