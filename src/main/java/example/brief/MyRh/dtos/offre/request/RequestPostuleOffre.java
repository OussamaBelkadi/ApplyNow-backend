package example.brief.MyRh.dtos.offre.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@Builder
public class RequestPostuleOffre {
    private Long offreId;
    private MultipartFile cv;
    private MultipartFile motivation;
    private long candidateId;
    private long societeId;
    private float balance;
}
