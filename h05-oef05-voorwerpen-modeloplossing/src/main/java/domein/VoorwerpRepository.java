package domein;

import java.util.ArrayList;
import java.util.List;

public class VoorwerpRepository
{
    private final List<Voorwerp> collectie;

    public VoorwerpRepository()
    {
        collectie = new ArrayList<>();
    }

    public void voegVoorwerpToe(Voorwerp voorwerp)
    {
        collectie.add(voorwerp);
    }

	public List<Voorwerp> getCollectie() 
	{
		return collectie;
	}
}
