package com.backend.boiler.plate.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

//import com.google.gson.Gson;
//
//import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "oauth_client_details")
public class OauthClient  implements Serializable, ClientDetails  {
	
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO,generator = "clients_generator")
    @SequenceGenerator(
            name = "clients_generator",
            sequenceName = "clients_sequence",
            initialValue = 1000,
			allocationSize = 1
    )
	@Column(
		    columnDefinition = "NUMERIC(19,0)"
		)
	private Long id;
	
	@Column(name="client_id")
	private String clientId;
	
	@Column(name="resource_ids")
	private String resourceIds;
	
	@Column(name="client_secret")
	private String clientSecret;
	
	@Column(name="scope")
	private String scope;
	
	@Column(name="authorized_grant_types")
	private String authorizedGrantTypes;
	
	@Column(name="web_server_redirect_uri")
	private String webServerRedirectUri;
	
	@Column(name="authorities")
	private String authorities;
	
	@Column(name="access_token_validity")
	private Integer accessTokenValidity;
	
	@Column(name="refresh_token_validity")
	private Integer refreshTokenValidity;
	
	@Column(name="additional_information")
	private String additionalInformation;
	
	@Column(name="autoapprove")
	private String autoApprove;

	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		try {
			String[] resourceIds = this.resourceIds.split(","); 
			return new HashSet(Arrays.asList(resourceIds));
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean isSecretRequired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Set<String> getScope() {
		try {
			String[] scopes = this.scope.split(","); 
			return new HashSet(Arrays.asList(scopes));
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		try {
			String[] grantTypes = this.authorizedGrantTypes.split(",");
			return new HashSet(Arrays.asList(grantTypes));
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> rls = new ArrayList<>();
		rls.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
		rls.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
		return null;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return accessTokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return refreshTokenValidity;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}


	@Override
	public Map<String, Object> getAdditionalInformation() {
		//return new Gson().fromJson(this.additionalInformation, HashMap.class);
		return null;
	}
	
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	
}

