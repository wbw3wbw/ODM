package service;

import java.util.List;

import model.OdAtt;


public interface IOdAttService {
    public List<OdAtt> findAll();
    public List<OdAtt> findByOdRecordId(Integer OdRecordId);
    public OdAtt findByID(Integer id);
    public void addOdAtt(OdAtt odAtt) ;
    public void delOdAtt(OdAtt odAtt);
    public void updateOdAtt(OdAtt odAtt);

}
