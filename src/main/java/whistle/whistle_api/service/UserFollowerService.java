package whistle.whistle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<UserFollowerDto> getUserFollower(Long userId) {
    List<UserFollower> followers = followerRepository.findUserFollowerByUserId(userId);
    return followers.stream().map(this::mapFollow).toList();
  }

  public void follow(User user, Long followerId) {
    Optional<User> following = userRepository.findById(followerId);
    if (!following.isPresent())
      throw new NotFoundException();
    Optional<UserFollower> checkFollow = followerRepository.findUserFollowerByUserIdAndFollowerId(user.getId(),
        followerId);
    if (checkFollow.isPresent())
      throw new ForbiddenException("User has Following");
    UserFollower follow = UserFollower.builder().user(user).follower(following.get()).build();
    followerRepository.save(follow);
  }

  public void unfollow(Long userId, Long followerId) {
    Optional<UserFollower> checkFollow = followerRepository.findUserFollowerByUserIdAndFollowerId(userId, followerId);
    if (!checkFollow.isPresent())
      throw new ForbiddenException("User has Unfollowing");
    followerRepository.deleteUserFollowerByUserIdAndFollowerId(userId, followerId);
  }

  private UserFollowerDto mapFollow(UserFollower follow) {
    return UserFollowerDto.builder().id(follow.getId()).userId(follow.getUser().getId())
        .name(follow.getFollower().getName()).followerId(follow.getFollower().getId())
        .build();
  }
}
