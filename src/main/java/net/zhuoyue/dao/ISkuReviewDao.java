package net.zhuoyue.dao;

import java.util.List;

import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.Sku;
import net.zhuoyue.vo.SkuReviewer;

public interface ISkuReviewDao {
    
    public void addSku(Sku sku);
    public void addSku(List<Sku> skuList);
    public void addReviewer(List<Reviewer> reviewerList);
    public void addSkuReviewer(List<SkuReviewer> skuRevList);
    
    public List<Sku> findAllSku();
    public List<Reviewer> findAllReviewer();
    public List<SkuReviewer> findSkuReviewer(String skuid);
    
}
