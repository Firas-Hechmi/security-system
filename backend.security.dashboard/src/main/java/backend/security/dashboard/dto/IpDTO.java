package backend.security.dashboard.dto;

import java.time.LocalDateTime;

public class IpDTO {

    private String address;

    private LocalDateTime date;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
