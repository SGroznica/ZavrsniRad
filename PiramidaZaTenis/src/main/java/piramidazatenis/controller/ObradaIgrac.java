/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piramidazatenis.controller;

import java.util.Arrays;
import java.util.List;
import piramidazatenis.model.Igrac;
import piramidazatenis.util.OibValidation;
import piramidazatenis.util.PiramidaZaTenisException;

/**
 *
 * @author stjep
 */
public class ObradaIgrac extends Obrada<Igrac> {

    @Override
    public List<Igrac> read() {
       return session.createQuery("from Igrac").list();
    }

     public List<Igrac> read(String uvjet) {
        return session.createQuery("from Igrac p "
                + " where concat(p.ime,' ',p.prezime,' ',ifnull(p.oib,'')) "
                + " like :uvjet order by p.prezime, p.ime")
                .setParameter("uvjet","%" + uvjet + "%")
                .setMaxResults(50)
                .list();
    }
     
     public List<Igrac> readPocetakPrezimena(String uvjet) {
      return session.createQuery("from Igrac p "
                + " where p.prezime "
                + " like :uvjet order by p.prezime, p.ime")
                .setParameter("uvjet", uvjet + "%")
                .setMaxResults(50)
                .list();
    }
    @Override
    protected void kontrolaCreate() throws PiramidaZaTenisException {
        kontrolaIme();
        kontrolaOib();
    }

    @Override
    protected void kontrolaUpdate() throws PiramidaZaTenisException {
       kontrolaIme();
       kontrolaOib();
    }

    @Override
    protected void kontrolaDelete() throws PiramidaZaTenisException {
       
    }

    private void kontrolaIme() throws PiramidaZaTenisException {
       char[] nedozvoljeno = {'1','2','.'};
       if(entitet.getIme()!=null){
           for(int i=0;i<entitet.getIme().length();i++){
               for(char c: nedozvoljeno){
                   if(entitet.getIme().charAt(i)==c){
                       throw new PiramidaZaTenisException("Ime ne smije imati jedan od znakova:\n" + 
                               Arrays.toString(nedozvoljeno));
                   }
               }
           }
       }
        
    }
    
    private void kontrolaOib() throws PiramidaZaTenisException {
        //https://github.com/domagojpa/oib-validation
        if (!OibValidation.checkOIB(entitet.getOib())) {
            throw new PiramidaZaTenisException("OIB nije formalno ispravan");
        }
    }

   

  
}
