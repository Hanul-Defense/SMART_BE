package org.example.smart.service;

import java.util.Collections;

import org.example.smart.domain.Military;
import org.example.smart.domain.Soldier;
import org.example.smart.domain.enums.MilitaryBranch;
import org.example.smart.domain.enums.MilitaryRank;
import org.example.smart.dto.request.PostSignInDto;
import org.example.smart.dto.request.PostSignUpDto;
import org.example.smart.dto.response.ResponseSignInDto;
import org.example.smart.dto.response.ResponseSignUpDto;
import org.example.smart.repository.MilitaryRepository;
import org.example.smart.repository.SoldierRepository;
import org.example.smart.security.model.CustomUserDetails;
import org.example.smart.security.util.JwtUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
	private final SoldierRepository soldierRepository;
	private final MilitaryRepository militaryRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public ResponseSignUpDto signUp(PostSignUpDto postSignUpDto) {

		try {
			Military military = militaryRepository.findByMilitaryBranchAndMilitaryNameAndCompanyAndPlatoon(
					MilitaryBranch.getMilitaryBranchByName(postSignUpDto.militaryBranch()),
					postSignUpDto.militaryName(),
					postSignUpDto.company(),
					postSignUpDto.platoon())
				.orElseThrow();

			Soldier soldier = Soldier.builder()
				.military(military)
				.name(postSignUpDto.soldierName())
				.serviceNumber(postSignUpDto.serviceNumber())
				.password(passwordEncoder.encode(postSignUpDto.password()))
				.enlistmentDate(postSignUpDto.enlistmentDate())
				.militaryRank(MilitaryRank.getMilitaryRankByRank(postSignUpDto.militaryRank()))
				.build();

			soldierRepository.save(soldier);
		} catch (Exception e) {
			return new ResponseSignUpDto(500,"회원가입에 실패했습니다.");
		}
		return new ResponseSignUpDto(201,"회원가입에 성공했습니다.");
	}

	public ResponseSignInDto signIn(PostSignInDto postSignInDto){
		Soldier soldier = soldierRepository.findByServiceNumber(postSignInDto.serviceNumber()).orElseThrow();
		boolean isValidatePassword = passwordEncoder.matches(postSignInDto.password(), soldier.getPassword());

		if(isValidatePassword){
			return new ResponseSignInDto(soldier.getId());
		}
		throw new RuntimeException();
	}

	@Override
	public UserDetails loadUserByUsername(String serviceNumber) throws AuthenticationException {
		Soldier soldier = soldierRepository.findByServiceNumber(serviceNumber).orElseThrow();

		return new CustomUserDetails(soldier.getId(), soldier.getServiceNumber(), soldier.getPassword(),
			soldier.getName(), Collections.emptyList());
	}
}
