package com.hyb.travel.service.impl;

import com.hyb.travel.dao.FavoriteDao;
import com.hyb.travel.dao.impl.FavoriteDaoImpl;
import com.hyb.travel.domain.Favorite;
import com.hyb.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;//如果对象有值，则为true，反之，则为false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
