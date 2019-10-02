package org.ashkan.view;

import java.util.List;
import javax.annotation.PostConstruct;
import org.ashkan.view.Theme;
import org.ashkan.view.ThemeService;

public class ThemeSwitcherView {

	private List<Theme> themes;

	private ThemeService service;

	@PostConstruct
	public void init() {
		service = new ThemeService();
		themes = service.getThemes();
		System.out.println(themes);
	}

	public List<Theme> getThemes() {
		return themes;
	} 

	public void setService(ThemeService service) {
		//this.service = service;
	}
}