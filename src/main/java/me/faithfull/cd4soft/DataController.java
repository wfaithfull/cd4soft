package me.faithfull.cd4soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will Faithfull
 */
@RestController
@RequestMapping("/")
public class DataController {

    final private DataProvider dataProvider;

    @Autowired
    public DataController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
    public ImportantData get() {
        return dataProvider.getData();
    }

    @RequestMapping(value = "/delay", method = RequestMethod.PUT, produces = "application/json")
    public void setDelay(@RequestBody long delay) {
        dataProvider.setDelay(delay);
    }

}
