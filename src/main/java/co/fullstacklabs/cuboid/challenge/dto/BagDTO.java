package co.fullstacklabs.cuboid.challenge.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

import co.fullstacklabs.cuboid.challenge.model.Bag;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagDTO {
    private Long id;

    @NotNull(message = "Bag volume can't be null.")
    private Double volume;

    @NotNull(message = "Bag title can't be null.")
    @Size(min = 1, max = Bag.TITLE_MAX_SIZE, message = "Bag title maximum size is " + Bag.TITLE_MAX_SIZE + " characters.")
    private String title;
    private Double payloadVolume;
    private Double availableVolume;
    private List<CuboidDTO> cuboids;
    
    public Double getPayloadVolume() {
    	if(payloadVolume == null) {
    		payloadVolume = volume == null || cuboids == null ? 0D : cuboids.stream().mapToDouble(c -> c.getVolume()).sum();
    	}
    	return payloadVolume;
    }
    
    public Double getAvailableVolume() {
    	if(availableVolume == null) {
    		availableVolume = volume == null || cuboids == null ? 0D : volume - cuboids.stream().mapToDouble(c -> c.getVolume()).sum();
    	}
    	return availableVolume;
    }
}
