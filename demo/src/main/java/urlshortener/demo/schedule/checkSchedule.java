package urlshortener.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.utils.CheckAlive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Component
public class checkSchedule {

    private static final Logger log = LoggerFactory.getLogger(checkSchedule.class);

    private final URIRepository uriService;

    @org.springframework.beans.factory.annotation.Autowired
    public checkSchedule(URIRepository uriService) {
        this.uriService = uriService;
    }

    @Scheduled(fixedRate = 5000)
    public void check() throws IOException {
        CheckAlive c = new CheckAlive();

        //HAY QUE CAMBIAR ESTE FRAGMENTO DE CÓDIGO Y RECUPERAR LA LISTA DE LA BD REAL
        List<URIItem> uris = uriService.comprobar();

        log.info("Comienza el checkeo de las URI...");
        for (int i=0; i < uris.size(); i++){
            log.info("COMPROBANDO URI " + uris.get(i).getRedirection());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (c.makeRequest(uris.get(i).getRedirection()) == 200){
                log.info("La URI " + uris.get(i).getRedirection() + " está viva.");
                //OK
                //Para esa URI, se registra la fecha actual como útima fecha en la que estuvo viva
            }
            else {
                log.info("La URI " + uris.get(i).getRedirection() + " no responde.");
                //Cualquier otra cosa aparte de un código 200 significará que la URI está muerta
                //Se obtiene la última vez que la URI estuvo viva
                //  -Si la diferencia entre la fecha actual y la fecha recuperada es >= K, entonces la URI se borra
            }
        }
    }

}

