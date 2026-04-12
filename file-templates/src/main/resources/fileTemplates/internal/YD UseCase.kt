#if (${ PACKAGE_NAME } && ${ PACKAGE_NAME } != "")package ${ PACKAGE_NAME }

#end
import javax.inject.Inject

class $ {NAME }UseCase @Inject constructor(
// TODO: Inject repositories
) {
    suspend operator fun invoke(): Result<Unit> {
        return runCatching {
            // TODO: Implement use case logic
        }
    }
}
