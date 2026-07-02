import { useEffect, useState } from 'react'
import { CButton, CCard, CCardBody, CCardHeader, CListGroup } from '@coreui/react'

import AccountRow from '../../components/AccountRow'
import { getAccounts } from '../../api/accountsApi'

const Accounts = () => {
  const [accounts, setAccounts] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const loadAccounts = async () => {
      try {
        const data = await getAccounts()
        setAccounts(data)
      } catch (error) {
        console.error('Failed to load acocunts:', error)
      } finally {
        setLoading(false)
      }
    }

    loadAccounts()
  }, [])

  return (
    <CCard>
      <CCardHeader className="d-flex justify-content-between align-items-center">
        <strong>Accounts</strong>
        <CButton color="primary" size="sm">
          Add Account
        </CButton>
      </CCardHeader>

      <CCardBody>
        {loading ? (
          <div>Loading accounts...</div>
        ) : accounts.length === 0 ? (
          <div>No accounts found.</div>
        ) : (
          <CListGroup flush>
            {accounts.map((account) => (
              <AccountRow
                key={account.id}
                accountNumber={account.accountNumber}
                balance={account.balance}
              />
            ))}
          </CListGroup>
        )}
      </CCardBody>
    </CCard>
  )
}

export default Accounts
