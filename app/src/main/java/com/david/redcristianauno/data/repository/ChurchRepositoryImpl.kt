package com.david.redcristianauno.data.repository

import com.david.redcristianauno.data.remote.RemoteChurchDataSource
import com.david.redcristianauno.domain.models.CellDataSource
import com.david.redcristianauno.domain.models.NetworkDataSource
import com.david.redcristianauno.domain.models.SubNetworkDataSource
import com.david.redcristianauno.vo.Resource
import javax.inject.Inject

class ChurchRepositoryImpl @Inject constructor(
    private val remoteChurchDataSource: RemoteChurchDataSource
) : ChurchRepository{

    override suspend fun getNetwork(church: String): Resource<List<NetworkDataSource>> =
        remoteChurchDataSource.getNetwork(church)

    override suspend fun getSubNetwork(
        church: String,
        network: String
    ): Resource<List<SubNetworkDataSource>> =
        remoteChurchDataSource.getSubNetwork(church, network)

    override suspend fun getCell(
        church: String,
        network: String,
        subNetwork: String
    ): Resource<List<CellDataSource>> {
        TODO("Not yet implemented")
    }
}