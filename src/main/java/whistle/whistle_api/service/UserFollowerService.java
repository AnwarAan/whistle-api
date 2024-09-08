package whistle.whistle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.UserFollowerDto;
import whistle.whistle_api.exception.ForbiddenException;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.model.UserFollower;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserFollowerRepository;
import whistle.whistle_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserFollowerService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final UserFollowerRepository followerRepository;

  public List<UserFollowerDto> getUserFollower(Long followerId) {
    List<UserFollower> followers = followerRepository.findByFollowerId(followerId);
    return followers.stream().map(this::mapFollow).toList();
  }

  @Transactional
  public void follow(User follower, Long followedId) {
    if (follower.getId() == followedId)
      throw new ForbiddenException("ID Must Different");

    Optional<User> followed = userRepository.findById(followedId);
    User updatedFollowed = followed.get();
    User updatedFollower = follower;
    Long countFollowed = follower.getTotalFollowed();
    Long countFollower = followed.get().getTotalFollower();
    Optional<UserFollower> checkFollow = followerRepository.findByFollowedIdAndFollowerId(followedId, follower.getId());
    UserFollower follow;
    if (followed.isEmpty()) {
      throw new NotFoundException();
    }
    if (checkFollow.isEmpty()) {
      updatedFollowed.setTotalFollower(countFollower + 1);
      updatedFollower.setTotalFollowed(countFollowed + 1);
      follow = UserFollower.builder().followed(followed.get()).follower(follower)
          .status(true).build();
      followerRepository.save(follow);
    } else if (checkFollow.isPresent() && checkFollow.get().getStatus() == false) {
      follow = checkFollow.get();
      follow.setStatus(true);
      updatedFollowed.setTotalFollower(countFollower + 1);
      updatedFollower.setTotalFollowed(countFollowed + 1);
      followerRepository.save(follow);
    } else if (checkFollow.isPresent() && checkFollow.get().getStatus() == true) {
      follow = checkFollow.get();
      follow.setStatus(false);
      updatedFollowed.setTotalFollower(countFollower - 1);
      updatedFollower.setTotalFollowed(countFollowed - 1);
      followerRepository.save(follow);
    }

  }

  private UserFollowerDto mapFollow(UserFollower userFollower) {
    return UserFollowerDto.builder().id(userFollower.getId())
        .followedId(userFollower.getFollowed().getId()).followerId(userFollower.getFollower().getId())
        .status(userFollower.getStatus())
        .build();
  }
}
