#if (${ PACKAGE_NAME } && ${ PACKAGE_NAME } != "")package ${ PACKAGE_NAME }

#end
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface $ {NAME }Repository {
    fun get${ NAME } s (): Flow<List<Any>> // TODO: Replace Any with your domain model
    suspend fun refresh()
}

class $ {NAME }RepositoryImpl @Inject constructor(
// TODO: Inject data sources
) : ${ NAME }Repository {

    override fun get${ NAME } s (): Flow<List<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }
}
