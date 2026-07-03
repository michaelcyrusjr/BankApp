import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CListGroup,
  CListGroupItem,
  CRow,
} from '@coreui/react'

import CIcon from '@coreui/icons-react'
import {
  cilSwapHorizontal,
  cilDollar,
  cilCloudUpload,
  cilDescription,
  cilCreditCard,
} from '@coreui/icons'

import SummaryCard from '../../components/SummaryCard'
import AccountRow from '../../components/AccountRow'
import TransactionRow from '../../components/TransactionRow'
import { useEffect, useState } from 'react'
import { getAccounts } from '../../api/accountsApi'

const Dashboard = () => {
  const [accounts, setAccounts] = useState([])
  const [loading, setLoading] = useState(true)
  console.log('render accounts:', accounts)

  useEffect(() => {
    const loadAccounts = async () => {
      try {
        const data = await getAccounts()
        console.log('fetched accounts:', data)
        setAccounts(data)
      } catch (error) {
        console.error('Failed to load accounts:', error)
      } finally {
        setLoading(false)
      }
    }

    loadAccounts()
  }, [])

  useEffect(() => {
    console.log('accounts changed:', accounts)
  }, [accounts])

  const totalBalance = '$64,046.00'
  const totalAccounts = accounts.length
  const totalIncome = '$4,850'
  const totalExpenses = '$2,310.00'
  const recentActivity = '5'

  const summaryCard = [
    {
      label: 'Total Balance',
      icon: cilSwapHorizontal,
      color: 'success',
      value: totalBalance,
    },
    {
      label: 'Total Accounts',
      icon: cilDollar,
      color: 'info',
      value: totalAccounts,
    },
    {
      label: 'Total Income',
      icon: cilCloudUpload,
      color: 'primary',
      value: totalIncome,
    },
    {
      label: 'Total Expenses',
      icon: cilDescription,
      color: 'warning',
      value: totalExpenses,
    },
  ]

  const transactions = [
    { name: 'Salary Deposit', date: 'May 24, 2026', amount: '+$3,200.00' },
    { name: 'Electric Bill', date: 'May 22, 2026', amount: '-120.00' },
    { name: 'Groceries', date: 'May 21, 2026', amount: '-$86.44' },
  ]

  const quickActions = [
    {
      label: 'Transfer Money',
      icon: cilSwapHorizontal,
      color: 'success',
    },
    {
      label: 'Pay Bills',
      icon: cilDollar,
      color: 'info',
    },
    {
      label: 'Deposit Check',
      icon: cilCloudUpload,
      color: 'primary',
    },
    {
      label: 'View Statements',
      icon: cilDescription,
      color: 'warning',
    },
  ]

  return (
    <>
      <CRow className="mb-4">
        <CCol xs={12}>
          <h1 className="mb-1">Welcome back, Michael!</h1>
          <p className="text-medium-emphasis mb-0">Here's an overview of your accounts today.</p>
        </CCol>
      </CRow>

      <CRow className="g-4 mb-4">
        {summaryCard.map((card) => (
          <CCol xs={12} sm={6} md={3} key={card.label}>
            <SummaryCard
              title={card.label}
              value={card.value}
              icon={card.icon}
              color={card.color}
            />
          </CCol>
        ))}
      </CRow>

      <CRow className="g-4">
        <CCol lg={6}>
          <CCard className="dashboard-list-card">
            <CCardHeader className="d-flex justify-content-between align-items-center">
              <strong>Accounts Overview</strong>
              <CButton color="primary" size="sm">
                Transfer Money
              </CButton>
            </CCardHeader>

            <CCardBody>
              <div className="accountsrow-grid">
                {accounts.map((account) => (
                  <CButton
                    key={account.id}
                    color="dark"
                    variant="outline"
                    className="accountsrow-button"
                  >
                    <span className={`accountsrow-icon text-${account.color || 'primary'}`}>
                      <CIcon icon={account.icon || cilCreditCard} size="lg" />
                    </span>

                    <span className="accountsrow-info">
                      <span className="accountsrow-label">Account</span>
                      <span className="accountsrow-number">{account.accountNumber}</span>
                    </span>

                    <span className="accountsrow-balance">{account.balance}</span>
                  </CButton>
                ))}
              </div>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol lg={6}>
          <CCard className="dashboard-list-card">
            <CCardHeader className="d-flex justify-content-between align-items-center">
              <strong>Recent Transactions</strong>
              <CButton color="link" size="sm" className="text-decoration-none p-0">
                View All
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CListGroup flush>
                {transactions.map((transaction) => (
                  <TransactionRow
                    key={transaction.name}
                    name={transaction.name}
                    date={transaction.date}
                    amount={transaction.amount}
                  />
                ))}
              </CListGroup>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol xs={12}>
          <CCard className="mt-1 quick-actions-card">
            <CCardBody>
              <strong className="quick-actions-title">Quick Actions</strong>

              <div className="quick-actions-grid">
                {quickActions.map((action) => (
                  <CButton
                    key={action.label}
                    color={action.color}
                    variant="outline"
                    className="quick-action-button"
                  >
                    <span className={`quick-action-icon text-${action.color}`}>
                      <CIcon icon={action.icon} size="lg" />
                    </span>

                    <span>{action.label}</span>
                  </CButton>
                ))}
              </div>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </>
  )
}

export default Dashboard
