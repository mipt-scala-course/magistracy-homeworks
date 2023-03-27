package mipt.homework6

import mipt.homework6.User.*
import mipt.homework6.UserErrors.*
import mipt.homework6.UserRepository.*

trait UserRepositoryDao {
  def findAll(config: Config): List[User]
  def create(name: UserName, age: Age, friends: Set[UserId] = Set.empty)(
      config: Config
  ): Either[UserAlreadyExists, User]
  def delete(userId: UserId)(config: Config): Either[UserDoesNotExists, Unit]
  def update(user: User)(config: Config): Either[UserDoesNotExists, Unit]
}
