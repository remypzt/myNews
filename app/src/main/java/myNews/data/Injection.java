package myNews.data;

import myNews.data.repositories.ArticlesRepository;
import myNews.data.service.API.FakeApiService;

/**
 * Created by Remy Pouzet on 25/11/2019.
 */

public class Injection
{
    public static ArticlesRepository createArticlesRepository()
    {
        return new ArticlesRepository(new FakeApiService());
    }
}