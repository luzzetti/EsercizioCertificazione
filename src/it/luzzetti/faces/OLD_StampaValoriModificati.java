package it.luzzetti.faces;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

@Deprecated
public class OLD_StampaValoriModificati implements ValueChangeListener {
    @Override
    public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
        System.out.println("Uh Oh! Qualcosa è cambiato!");
        System.out.println(valueChangeEvent.getSource());
        System.out.println(valueChangeEvent.getNewValue());
    }
}
